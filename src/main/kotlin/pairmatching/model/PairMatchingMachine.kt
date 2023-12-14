package pairmatching.model

import camp.nextstep.edu.missionutils.Randoms


class PairMatchingMachine {

    val pairMatchingResult = mutableListOf<List<String>>()
    val isValidMatch = false

    fun match(course: String, crews: List<String>) {
        do {
            shuffle(crews)
            println("\n\n\n@@@@@@@@@@@@@@@@@@@ 셔플 결과 :")
            pairMatchingResult.forEach { println(it) }
        } while (!isValidMatch)
//        val crews = crewNames.map { Crew(matchingInfo[0].stringToCourse(), it) }
//        crews.forEach { crew -> crew.printInfo() }
    }

    private fun shuffle(crewNames: List<String>) {
        when (crewNames.size / 2 == 0) {
            true -> pairMatch(Randoms.shuffle(crewNames))
            false -> tripleMatch(Randoms.shuffle(crewNames))
        }
    }

    private fun pairMatch(crewNamse: List<String>) {
        for (index in 0 until crewNamse.size step 2) {
            pairMatchingResult.add(listOf(crewNamse[index], crewNamse[index + 1]))
        }
    }

    private fun tripleMatch(crewNamse: List<String>) {
        for (index in 0 until crewNamse.size - 3 step 2) {
            pairMatchingResult.add(listOf(crewNamse[index], crewNamse[index + 1]))
        }
        pairMatchingResult.add(
            listOf(
                crewNamse[crewNamse.size - 3], crewNamse[crewNamse.size - 2], crewNamse[crewNamse.size - 1]
            )
        )
    }

}