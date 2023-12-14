package pairmatching.model

import java.io.File

class CrewNameReader {

    fun read(course: String): List<String> {
        return when (course) {
            Course.BACKEND.value -> getCrewNameByFileName("backend-crew.md")
            Course.FRONTEND.value -> getCrewNameByFileName("frontend-crew.md")
            else -> throw IllegalArgumentException("[ERROR] 올바른 코스가 아닙니다")
        }
    }

    private fun getCrewNameByFileName(fileName: String): List<String> {
        val directoryPath = "src/main/kotlin/resources" // 여기에 읽어올 디렉토리 경로를 입력하세요.
        val markdownFiles = File(directoryPath).walk()
            .filter { it.isFile && it.extension == "md" && it.name == fileName } // md 확장자를 가진 파일만 필터링
            .toList()
        return markdownFiles.flatMap { file -> file.readLines() }

//        markdownFiles.forEach { file ->
//            println("File name: ${file.name}")
//            println("File content: \n${file.readText()}")
    }
}