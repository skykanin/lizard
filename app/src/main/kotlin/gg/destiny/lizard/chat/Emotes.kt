package gg.destiny.lizard.chat

import android.graphics.drawable.Drawable
import gg.destiny.lizard.App
import gg.destiny.lizard.R

data class Emote(val id: Int, val x: Int, val y: Int, val width: Int, val height: Int) {
  val drawable: Drawable by lazy {
    val drawable = App.INSTANCE.getDrawable(id)
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    return@lazy drawable
  }

  val span by lazy { EmoteSpan(drawable) }
}

val EMOTE_MAP = hashMapOf(
    "AYYYLMAO" to Emote(R.drawable.ayyylmao, 0, 0, 100, 30),
    "AngelThump" to Emote(R.drawable.angelthump, 0, -30, 95, 30),
    "Abathur" to Emote(R.drawable.abathur, 0, -60, 82, 30),
    "GameOfThrows" to Emote(R.drawable.gameofthrows, -100, 0, 79, 30),
    "DANKMEMES" to Emote(R.drawable.dankmemes, -100, -30, 72, 30),
    "DuckerZ" to Emote(R.drawable.duckerz, -100, -60, 56, 26),
    "SoDoge" to Emote(R.drawable.sodoge, 0, -90, 52, 30),
    "SLEEPSTINY" to Emote(R.drawable.sleepstiny, -52, -90, 50, 30),
    "PICNIC" to Emote(R.drawable.picnic, -102, -90, 50, 20),
    "TRUMPED" to Emote(R.drawable.trumped, 0, -120, 49, 30),
    "NOTMYTEMPO" to Emote(R.drawable.notmytempo, -49, -120, 47, 30),
    "SoSad" to Emote(R.drawable.sosad, -96, -120, 45, 30),
    "LIES" to Emote(R.drawable.lies, -179, 0, 45, 30),
    "BERN" to Emote(R.drawable.bern, -179, -30, 45, 30),
    "KappaRoss" to Emote(R.drawable.kappaross, -179, -60, 44, 30),
    "CheekerZ" to Emote(R.drawable.cheekerz, -179, -90, 44, 30),
    "FrankerZ" to Emote(R.drawable.frankerz, -179, -120, 42, 30),
    "ASLAN" to Emote(R.drawable.aslan, 0, -150, 41, 30),
    "SOTRIGGERED" to Emote(R.drawable.sotriggered, -41, -150, 40, 30),
    "ChibiDesti" to Emote(R.drawable.chibidesti, -81, -150, 40, 30),
    "BASEDWATM8" to Emote(R.drawable.basedwatm8, -141, -120, 38, 30),
    "Heimerdonger" to Emote(R.drawable.heimerdonger, -121, -150, 37, 30),
    "FeedNathan" to Emote(R.drawable.feednathan, -158, -150, 37, 30),
    "MASTERB8" to Emote(R.drawable.masterb8, 0, -180, 36, 29),
    "LUL" to Emote(R.drawable.lul, -224, 0, 36, 30),
    "DJAslan" to Emote(R.drawable.djaslan, -224, -30, 32, 36),
    "BibleThump" to Emote(R.drawable.biblethump, -224, -66, 36, 30),
    "INFESTINY" to Emote(R.drawable.infestiny, -224, -96, 35, 30),
    "Hhhehhehe" to Emote(R.drawable.hhhehhehe, -224, -126, 35, 25),
    "SpookerZ" to Emote(R.drawable.spookerz, -224, -151, 34, 30),
    "SWEATSTINY" to Emote(R.drawable.sweatstiny, 0, -209, 34, 30),
    "WEEWOO" to Emote(R.drawable.weewoo, -34, -209, 33, 30),
    "SURPRISE" to Emote(R.drawable.surprise, -67, -209, 33, 30),
    "OverRustle" to Emote(R.drawable.overrustle, -100, -209, 33, 30),
    "LeRuse" to Emote(R.drawable.leruse, -133, -209, 33, 30),
    "Klappa" to Emote(R.drawable.klappa, -166, -209, 33, 30),
    "HmmStiny" to Emote(R.drawable.hmmstiny, -199, -209, 33, 30),
    "FerretLOL" to Emote(R.drawable.ferretlol, -260, 0, 33, 30),
    "PepoThink" to Emote(R.drawable.pepothink, -260, -30, 32, 32),
    "DestiSenpaii" to Emote(R.drawable.destisenpaii, -260, -62, 32, 30),
    "NoTears" to Emote(R.drawable.notears, -260, -92, 31, 30),
    "haHAA" to Emote(R.drawable.hahaa, -260, -122, 30, 30),
    "gachiGASM" to Emote(R.drawable.gachigasm, -260, -152, 30, 30),
    "YEE" to Emote(R.drawable.yee, -260, -182, 30, 30),
    "Wowee" to Emote(R.drawable.wowee, 0, -239, 30, 30),
    "WhoahDude" to Emote(R.drawable.whoahdude, -195, -150, 25, 30),
    "WORTH" to Emote(R.drawable.worth, -232, -209, 21, 30),
    "UWOTM8" to Emote(R.drawable.uwotm8, -30, -239, 28, 30),
    "Sippy" to Emote(R.drawable.sippy, -58, -239, 24, 30),
    "PEPE" to Emote(R.drawable.pepe, -82, -239, 30, 30),
    "OhMyDog" to Emote(R.drawable.ohmydog, -112, -239, 30, 30),
    "OhKrappa" to Emote(R.drawable.ohkrappa, -142, -239, 23, 30),
    "Nappa" to Emote(R.drawable.nappa, -165, -239, 22, 30),
    "NOBULLY" to Emote(R.drawable.nobully, -187, -239, 30, 30),
    "MotherFuckinGame" to Emote(R.drawable.motherfuckingame, -217, -239, 30, 30),
    "Memegasm" to Emote(R.drawable.memegasm, -247, -239, 26, 30),
    "MLADY" to Emote(R.drawable.mlady, -293, 0, 30, 30),
    "Kappa" to Emote(R.drawable.kappa, -293, -30, 22, 30),
    "FeelsGoodMan" to Emote(R.drawable.feelsgoodman, -293, -60, 30, 30),
    "FeelsBadMan" to Emote(R.drawable.feelsbadman, -293, -90, 30, 30),
    "FeelsAmazingMan" to Emote(R.drawable.feelsamazingman, -293, -120, 30, 30),
    "Dravewin" to Emote(R.drawable.dravewin, -293, -150, 30, 30),
    "Disgustiny" to Emote(R.drawable.disgustiny, -293, -180, 28, 30),
    "DatGeoff" to Emote(R.drawable.datgeoff, -293, -210, 21, 30),
    "DappaKappa" to Emote(R.drawable.dappakappa, 0, -269, 26, 30),
    "DaFeels" to Emote(R.drawable.dafeels, -26, -269, 30, 30),
    "DURRSTINY" to Emote(R.drawable.durrstiny, -56, -269, 21, 30),
    "DAFUK" to Emote(R.drawable.dafuk, -77, -269, 30, 30),
    "CallChad" to Emote(R.drawable.callchad, -107, -269, 24, 30),
    "CallCatz" to Emote(R.drawable.callcatz, -131, -269, 26, 30),
    "BasedGod" to Emote(R.drawable.basedgod, -157, -269, 29, 30),
    "nathanYee" to Emote(R.drawable.nathanyee, -293, -240, 28, 28),
    "nathanWeeb" to Emote(R.drawable.nathanweeb, -224, -181, 28, 28),
    "nathanWat" to Emote(R.drawable.nathanwat, -36, -180, 28, 28),
    "nathanTowel" to Emote(R.drawable.nathantowel, -64, -180, 28, 28),
    "nathanThinking" to Emote(R.drawable.nathanthinking, -92, -180, 28, 28),
    "nathanRustle" to Emote(R.drawable.nathanrustle, -120, -180, 28, 28),
    "nathanRuse" to Emote(R.drawable.nathanruse, -148, -180, 28, 28),
    "nathanPepe" to Emote(R.drawable.nathanpepe, -176, -180, 28, 28),
    "nathanNotears" to Emote(R.drawable.nathannotears, -186, -269, 28, 28),
    "nathanF" to Emote(R.drawable.nathanf, -214, -269, 28, 28),
    "nathanDank" to Emote(R.drawable.nathandank, -242, -269, 28, 28),
    "nathanD" to Emote(R.drawable.nathand, -270, -269, 28, 28),
    "POTATO" to Emote(R.drawable.potato, -323, 0, 28, 28),
    "FIDGETLOL" to Emote(R.drawable.fidgetlol, -323, -28, 25, 25)
)
