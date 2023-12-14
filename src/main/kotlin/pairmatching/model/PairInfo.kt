package pairmatching.model

data class PairInfo(
    val course: Course,
    val level: Level,
    val mission: Mission,
    val crewPairs: List<Set<String>>
)