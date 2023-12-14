package pairmatching.model

enum class Level(val level: String) {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    companion object {
        fun String.stringToLevel(): Level {
            return when (this) {
                LEVEL1.level -> LEVEL1
                LEVEL2.level -> LEVEL2
                LEVEL3.level -> LEVEL3
                LEVEL4.level -> LEVEL4
                LEVEL5.level -> LEVEL5
                else -> throw IllegalArgumentException("\n[ERROR] 레벨은 1부터 5중에서 입력하세요.")
            }
        }
    }
}