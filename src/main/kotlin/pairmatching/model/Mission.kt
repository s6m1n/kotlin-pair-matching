package pairmatching.model

enum class Mission(val mission: String) {
    RACING_CAR("자동차경주"),
    LOTTO("로또"),
    BASEBALL("숫자야구게임"),
    SHOPPING_CART("장바구니"),
    PAYMENT("결제"),
    SUBWAY("지하철노선도"),
    IMPROVEMENT("성능개선"),
    RELEASE("배포"),
    NONE("없음");

    companion object {
        fun String.stringToMission(): Mission {
            return when (this) {
                RACING_CAR.mission -> RACING_CAR
                LOTTO.mission -> LOTTO
                BASEBALL.mission -> BASEBALL
                SHOPPING_CART.mission -> SHOPPING_CART
                PAYMENT.mission -> PAYMENT
                SUBWAY.mission -> SUBWAY
                IMPROVEMENT.mission -> IMPROVEMENT
                RELEASE.mission -> RELEASE
                NONE.mission -> NONE
                else -> throw IllegalArgumentException("[ERROR] 적절한 미션을 입력하세요.")
            }
        }
    }
}