package pairmatching.controller

import pairmatching.view.InputView
import pairmatching.view.OutputView

class MatchingController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private var command: String = ""
) {
    fun start() {
        do {
            when (getValidInput()) {
                "1" -> pairMatching()
                "2" -> pairCheck()
                "3" -> pairReset()
                "Q" -> break
            }
        } while (command != "Q")
    }

    private fun getValidInput(): String {
        return try {
            inputView.readValidInput()
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidInput()
        }
    }

    private fun pairMatching() {
        println("페어 매칭")
    }

    private fun pairReset() {
        println("페어 초기화")
    }

    private fun pairCheck() {
        println("페어 조회")
    }
}