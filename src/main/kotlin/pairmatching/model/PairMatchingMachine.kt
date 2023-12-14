package pairmatching.model

import camp.nextstep.edu.missionutils.Randoms


class PairMatchingMachine(
    private val matchingInfo: List<String>, // 코스, 레벨, 미션 정보
    private val crewNames: List<String>,
    private val pairMatchingRepository: PairMatchingRepository
) {
    private val pairMatchingResult = mutableListOf<List<String>>() // 페어 매칭 결과를 저장
    fun match() {
        pairMatchingResult.clear()
        for (i in 1..3) {
            val shuffledCrews = Randoms.shuffle(crewNames)
            println("\n$i 번째 매칭 결과")
            shuffledCrews.forEach { print("$it ") }
            println("\n")
            if (pairMatchingRepository.isValidMatch(matchingInfo, shuffledCrews)) {
                break
            }
            if(i == 3) println("[ERROR] 매칭이 되지 않거나 매칭을 할 수 있는 경우의 수가 없습니다")
        }
        val result = pairMatchingRepository.getPair(matchingInfo)?.crewPairs
//        result.forEach { println(it) }
//        pairMatchingResult.add(pairMatch(shuffledCrews))
    }


//    private fun convertToCrew() { // String을 Crew로 변환
//        val crews = pairMatchingResult.map { it.forEach { Crew(matchingInfo[0].stringToCourse(), it) } }
//        crews.forEach { crew -> crew.printInfo() }
//    }

}