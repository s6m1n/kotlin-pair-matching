package pairmatching.model

import camp.nextstep.edu.missionutils.Randoms


class PairMatchingMachine(
    private val matchingInfo: List<String>, // 코스, 레벨, 미션 정보
    private val crewNames: List<String>,
    private val pairMatchingRepository: PairMatchingRepository
) {

    fun match(): List<Set<String>>? {
        for (i in 1..3) {
            val shuffledCrews = Randoms.shuffle(crewNames)
            if (pairMatchingRepository.isValidMatch(matchingInfo, shuffledCrews)) {
                return pairMatchingRepository.getPair(matchingInfo)?.crewPairs
            }
        }
        return null
    }
}