package gg.destiny.lizard.chat

import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v4.text.util.LinkifyCompat
import android.support.v7.widget.RecyclerView
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.TextAppearanceSpan
import android.text.util.Linkify
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import com.github.ajalt.flexadapter.FlexAdapter
import com.github.ajalt.flexadapter.register
import gg.destiny.lizard.App
import gg.destiny.lizard.R
import gg.destiny.lizard.account.AccountFeature
import gg.destiny.lizard.base.text.Spanner
import gg.destiny.lizard.core.chat.ChatGuiPackage
import gg.destiny.lizard.core.chat.ChatSocket
import kotlinx.android.synthetic.main.item_chat_connection.view.chat_connection_message
import kotlinx.android.synthetic.main.item_chat_message.view.chat_message_message

data class ComboMessage(
    val emoteSpan: EmoteSpan,
    var count: Int = 2,
    var completed: Boolean = false,
    var ticked: Boolean = true
) {
  fun bind(view: TextView) {
    emoteSpan.setVisible(App.get().applicationContext, true)
    view.text = Spanner()
        .pushPopSpan(emoteSpan)
        .pushSpan(ForegroundColorSpan(Color.WHITE))
        .pushSpan(AbsoluteSizeSpan((view.textSize * (1 + 0.05f * count)).toInt(), false))
        .append(" ${count}x ")
        .popSpan()
        .popSpan()
        .pushSpan(ForegroundColorSpan(0xFFAAAAAA.toInt()))
        .append(if (completed) "C-C-C-COMBO" else "HITS")
        .build()
    ticked = false
  }
}

fun createChatAdapter(chatGuiPackage: () -> ChatGuiPackage, highlightNick: () -> String?)
    : FlexAdapter<Any> {
  return FlexAdapter<Any>().apply {
    register<ChatSocket.Message.UserMessage>(R.layout.item_chat_message) { message, view, i ->
      val previousMessage = if (i > 0) items[i - 1] else null
      val continuationMessage =
          previousMessage is ChatSocket.Message.UserMessage && previousMessage.nick == message.nick
      message.bind(chatGuiPackage(), view.chat_message_message, continuationMessage)

      val nick = highlightNick()
      val data = message.data
      view.setBackgroundColor(
          if (nick != null && (data.contains(" $nick ") || data.startsWith("$nick "))) {
            ContextCompat.getColor(view.context, R.color.white10)
          } else {
            Color.TRANSPARENT
          }
      )
    }

    register<ComboMessage>(R.layout.item_chat_message) { combo, view, _ ->
      combo.bind(view.chat_message_message)
    }

    register<ChatSocket.Message.Names>(R.layout.item_chat_connection) { message, view, _ ->
      view.chat_connection_message.text =
          view.context
              .resources
              .getQuantityString(
                  R.plurals.chat_connection_message,
                  message.connectioncount,
                  message.connectioncount)
    }
  }
}

private val chatTextColorSpan = ForegroundColorSpan(0xFFAAAAAA.toInt())
private val greenTextColorSpan = ForegroundColorSpan(0xFF6CA528.toInt())
private val meTextStyleSpan = StyleSpan(Typeface.ITALIC)

private fun ChatSocket.Message.UserMessage.bind(
    packageInfo: ChatGuiPackage,
    message: TextView,
    isContinuation: Boolean
) {
  val spanner = Spanner()
  val isMe = data.startsWith("/me ")
  if (!isContinuation) {
    spanner
        .pushSpan(ForegroundColorSpan(colorForFeatures(features)))
        .append(nick)
        .popSpan()

    if (!isMe) {
      spanner.append(": ")
    }
  } else {
    spanner.pushSpan(ForegroundColorSpan(0xFF333333.toInt())).append("> ").popSpan()
  }

  spanner.pushSpan(
      when (data.firstOrNull()) {
        '>' -> greenTextColorSpan
        else -> chatTextColorSpan
      })

  if (isMe) {
    spanner.pushSpan(meTextStyleSpan)
  }

  data.split(' ')
      .forEachIndexed { index, s ->
        val emote = packageInfo.emoteMap[s]
        if (emote != null) {
          val span = EmoteSpan(App.get().applicationContext, emote, { message.invalidate() })
          span.setVisible(App.get().applicationContext, true)
          when (emote.name) {
            "REE", "OverRustle" -> rage(span, message)
            "MLADY" -> tipTip(span, message)
            "DANKMEMES" -> dankHueShift(span, message)
          }
          spanner.append(' ')
              .pushPopSpan(span)
              .append(' ')
        } else if (!isMe || s != "/me") {
          spanner.append(if (index != 0) " $s" else s)
        }
      }

  message.text = spanner.build()
  LinkifyCompat.addLinks(message, Linkify.WEB_URLS)
}

private fun tipTip(span: EmoteSpan, view: TextView) {
  ObjectAnimator.ofFloat(span, EmoteSpan.RotateProperty, 0f, 15f, 0f, 15f, 0f).apply {
    addUpdateListener { view.invalidate() }
    interpolator = AccelerateDecelerateInterpolator()
    duration = 500
    start()
  }
}

private fun rage(span: EmoteSpan, view: TextView) {
  ObjectAnimator.ofFloat(span, EmoteSpan.TranslateXProperty, 0f, -5f, 0f, 5f, 0f).apply {
    addUpdateListener { view.invalidate() }
    interpolator = AccelerateDecelerateInterpolator()
    duration = 100
    repeatCount = 3
    start()
  }
}

private fun dankHueShift(span: EmoteSpan, view: TextView) {
  ObjectAnimator.ofFloat(span, EmoteSpan.HueShiftProperty, 0f, 360f, 0f).apply {
    addUpdateListener { view.invalidate() }
    interpolator = AccelerateDecelerateInterpolator()
    duration = 1000
    start()
  }
}

private fun colorForFeatures(features: List<String>): Int {
  return features.map { AccountFeature.of(it) }
      .sortedByDescending { it.priority }
      .firstOrNull()?.color ?: Color.WHITE
}
