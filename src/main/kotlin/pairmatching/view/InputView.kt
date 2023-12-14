package pairmatching.view

import camp.nextstep.edu.missionutils.Console

class InputView {

    fun readValidCommand(): String = validateCommand(Console.readLine())

    private fun validateCommand(input: String): String {
        require(input in listOf<String>("1", "2", "3", "Q")) { "[ERROR] 올바르지 않은 입력값입니다" }
        return input
    }

    fun readValidRematchCommand(): String = validateRematchCommand(Console.readLine())


    private fun validateRematchCommand(input: String): String {
        require(input in listOf<String>("네", "아니오")) { "[ERROR] 올바르지 않은 입력값입니다" }
        return input
    }

    fun readValidMatchingInfo(): List<String> = validateMatchingInfo(Console.readLine())

    private fun validateMatchingInfo(input: String): List<String> {
        val parsed = input.split(", ")
        require(parsed.size == 2 || parsed.size == 3) { "[ERROR] 입력 형식이 올바르지 않습니다" }
        return parsed
    }
}