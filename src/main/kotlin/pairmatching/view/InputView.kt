package pairmatching.view

import camp.nextstep.edu.missionutils.Console

class InputView {

    fun readValidInput(): String = validateInput(Console.readLine())

    private fun validateInput(input: String): String {
        require(input in listOf<String>("1", "2", "3", "Q")) { "[ERROR] 올바르지 않은 입력값입니다." }
        return input
    }
}