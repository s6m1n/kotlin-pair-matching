package pairmatching.model

import pairmatching.model.Course.Companion.stringToCourse
import pairmatching.model.Level.Companion.stringToLevel
import pairmatching.model.Mission.Companion.stringToMission

class PairMatchingRepository() {

    private val matchingResults = mutableSetOf<PairInfo>()

    fun getPair(matchingInfo: List<String>): PairInfo? {
        val tmpPair = makePairInfo(matchingInfo, pareUp(listOf("", "")))
        return matchingResults.find {
            isSameCourse(it, tmpPair) && isSameLevel(it, tmpPair) && isSameMission(it, tmpPair)
        }
    }

    // matchingInfo : [프론트엔드, 레벨1, 자동차경주] // crewNames : [소민, 정주, 어쩌구, 저쩌구]
    fun isValidMatch(matchingInfo: List<String>, crewNames: List<String>): Boolean {
        val newCrewPair = pareUp(crewNames)
        val crewPairInfo = makePairInfo(matchingInfo, newCrewPair)
        val comparePairInfo = matchingResults.filter { // 코스와 레벨이 같고 미션이 다른 PairInfo 리스트
            isSameCourse(it, crewPairInfo) && isSameLevel(it, crewPairInfo) && !isSameMission(it, crewPairInfo)
        }
        val existingCrewPairs = comparePairInfo.map { it.crewPairs } // 겹치는지 비교할 Pair들 리스트
        val isValidMatch = !existingCrewPairs.any { isOverlap(it, newCrewPair) }
        if (isValidMatch) {
            matchingResults.add(crewPairInfo)
        }
        return isValidMatch

//        val matchSize = matchingResults.size
//        val newMatchSize = matchingResults.size
    }

    private fun printCrewPairs(crewPair: List<Set<String>>) {
        crewPair.forEach { set -> println(set.joinToString(" : ")) }
    }

    // 정보를 PairInfo 객체로 만들어줌
    private fun makePairInfo(matchingInfo: List<String>, crewName: List<Set<String>>): PairInfo {
        return PairInfo(
            matchingInfo[0].stringToCourse(),
            matchingInfo[1].stringToLevel(),
            matchingInfo[2].stringToMission(),
            crewName
        )
    }

    // 페어 짝 짓기
    private fun pareUp(crewNames: List<String>): List<Set<String>> {
        return when (crewNames.size % 2 == 0) {
            true -> pairLast(crewNames)
            false -> tripleLast(crewNames)
        }
    }

    private fun isOverlap(
        existingCrewPair: List<Set<String>>,
        newCrewPair: List<Set<String>>
    ): Boolean {
        return (existingCrewPair.size + newCrewPair.size != (existingCrewPair+newCrewPair).toSet().size)
    }


    fun alreadyExist(matchingInfo: List<String>): Boolean {
        val tmpPair = makePairInfo(matchingInfo, pareUp(listOf("")))
        return matchingResults.any {
            isSameCourse(it, tmpPair) && isSameLevel(it, tmpPair) && isSameMission(it, tmpPair)
        }
    }

    private fun isSameCourse(pairInfo1: PairInfo, pairInfo2: PairInfo) = pairInfo1.course == pairInfo2.course

    private fun isSameLevel(pairInfo1: PairInfo, pairInfo2: PairInfo) = pairInfo1.level == pairInfo2.level

    private fun isSameMission(pairInfo1: PairInfo, pairInfo2: PairInfo) = pairInfo1.mission == pairInfo2.mission


    private fun pairLast(crewNamse: List<String>): MutableList<Set<String>> {
        val pairMatchingResult = mutableListOf<Set<String>>()
        for (index in 0 until crewNamse.size step 2) {
            pairMatchingResult.add(setOf(crewNamse[index], crewNamse[index + 1]))
        }
        return pairMatchingResult
    }

    private fun tripleLast(crewNamse: List<String>): MutableList<Set<String>> {
        val pairMatchingResult = mutableListOf<Set<String>>()
        for (index in 0 until crewNamse.size - 3 step 2) {
            pairMatchingResult.add(setOf(crewNamse[index], crewNamse[index + 1]))
        }
        pairMatchingResult.add(
            setOf(
                crewNamse[crewNamse.size - 3], crewNamse[crewNamse.size - 2], crewNamse[crewNamse.size - 1]
            )
        )
        return pairMatchingResult
    }
}