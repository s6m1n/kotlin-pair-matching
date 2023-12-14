package pairmatching.controller

import pairmatching.model.Course.Companion.stringToCourse
import pairmatching.model.Crew
import pairmatching.model.CrewNameReader
import pairmatching.model.PairMatchingMachine
import pairmatching.view.InputView
import pairmatching.view.OutputView

class MatchingController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private var command: String = ""
) {
    fun start() {
        do {
            outputView.printStartMessage()
            when (getValidCommand()) {
                "1" -> pairMatching()
                "2" -> pairCheck()
                "3" -> pairReset()
                "Q" -> break
            }
        } while (command != "Q")
    }

    private fun getValidCommand(): String {
        return try {
            inputView.readValidCommand()
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidCommand()
        }
    }

    private fun pairMatching() {
        outputView.printCourseAndMission()
        val matchingInfo = getValidMatchingInfo()
        val crewNames = CrewNameReader().read(matchingInfo[0])
        PairMatchingMachine().match(matchingInfo[0],crewNames) // 불러온 List<String>으로 매칭 진행
    }

    private fun getValidMatchingInfo(): List<String> {
        return try {
            val input = inputView.readValidMatchingInfo()
            input[0].stringToCourse()
            input
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidMatchingInfo()
        }
    }

    private fun pairCheck() {
        println("페어 조회\n")
    }

    private fun pairReset() {
        println("페어 초기화\n")
    }
}