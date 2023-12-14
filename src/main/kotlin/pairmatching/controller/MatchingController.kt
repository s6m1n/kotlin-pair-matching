package pairmatching.controller

import pairmatching.model.Course.Companion.stringToCourse
import pairmatching.model.CrewNameReader
import pairmatching.model.PairMatchingMachine
import pairmatching.model.PairMatchingRepository
import pairmatching.view.InputView
import pairmatching.view.OutputView

class MatchingController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val pairMatchingRepository: PairMatchingRepository = PairMatchingRepository()
) {
    private var command: String = ""
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
        val matchingInfo = getValidMatchingInfo() // 코스, 레벨, 미션
        val crewNames = CrewNameReader().read(matchingInfo[0]) // 코스에 해당하는 크루명 읽어오기
        val pairMatchingMachine = PairMatchingMachine(matchingInfo, crewNames, pairMatchingRepository)
        if (pairMatchingRepository.alreadyExist(matchingInfo)) {
            outputView.printRematchMessage()
            checkRematch(pairMatchingMachine)
        } else {
            outputView.printPairMatchingResult(pairMatchingMachine.match())
        }
    }

    private fun checkRematch(pairMatchingMachine: PairMatchingMachine) {
        when (getRematchCommand() == "네") {
            true -> outputView.printPairMatchingResult(pairMatchingMachine.match())
            false -> return
        }
    }

    private fun getRematchCommand(): String {
        return try {
            inputView.readValidRematchCommand()
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getRematchCommand()
        }
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