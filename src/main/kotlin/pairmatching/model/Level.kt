package pairmatching.model

enum class Level(val level: String, val mission: List<Mission?>) {
    LEVEL1("레벨1", listOf(Mission.RACING_CAR, Mission.LOTTO, Mission.BASEBALL)),
    LEVEL2("레벨2", listOf(Mission.SHOPPING_CART, Mission.PAYMENT, Mission.SUBWAY)),
    LEVEL3("레벨3", listOf()),
    LEVEL4("레벨4", listOf(Mission.IMPROVEMENT, Mission.RELEASE)),
    LEVEL5("레벨5", listOf());

    companion object {
        fun String.stringToLevel(): Level {
            return when (this) {
                LEVEL1.level -> LEVEL1
                LEVEL2.level -> LEVEL2
                LEVEL3.level -> LEVEL3
                LEVEL4.level -> LEVEL4
                LEVEL5.level -> LEVEL5
                else -> throw IllegalArgumentException("[ERROR] 레벨은 1부터 5중에서 입력하세요.")
            }
        }
    }
}