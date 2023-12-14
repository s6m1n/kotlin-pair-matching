package pairmatching.model

enum class Course(val value: String) {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    companion object {
        fun String.stringToCourse(): Course {
            return when (this) {
                BACKEND.value -> BACKEND
                FRONTEND.value -> FRONTEND
                else -> throw IllegalArgumentException("\n[ERROR] 코스는 프론트엔드, 백엔드 중 입력하세요")
            }
        }
    }
}