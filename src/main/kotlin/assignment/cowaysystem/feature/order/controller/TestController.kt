package assignment.cowaysystem.feature.order.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class TestController {
    /**
     * @GetMapping("/jwt")
    fun jwtGetTest(): String{
    return "jwt get test"
    }

    @PostMapping("/jwt")
    fun jwtPostTest(): String{
    return "jwt post test"
    }

    @GetMapping("/members")
    fun members(): String{
    return "into members"
    }
    @GetMapping("/managers")
    fun managers(): String{
    return "into managers"
    }

    @GetMapping("/admins")
    fun admins(): String{
    return "into admins"
    }

    /**
     * pathVariable 테스트
    */
    @GetMapping("/test/{path}")
    fun getPathVariableTest(@PathVariable("path") path: Long): String{
    return "key : $path"
    }

    /**
     * 쿼리 파라미터 테스트, 주로 GET 에서 검색 조건으로 많이 사용됨
    */
    @GetMapping("/test")
    fun getQueryParamTest(@RequestParam("id") key: String): String{
    return "key : ${key}"
    }

    /**
     * post 방식으로 보낼때
     * 클라이언트에서 form 형식으로 body(쿼리파라미터랑 비슷한 형식)에 데이터를 담아 서버로 보낼때
    */
    @PostMapping("/test")
    fun postFormTest(@RequestBody request: String): String{
    return "post : $request"
    }

    /**
     * get 방식으로 보낼때
     * 클라이언트에서 form 형식으로 데이터를 보낼때, (get 방식이면 -> 쿼리 파라미터로 보낸다.)
     * @RequestBody는 get방식이 바디를 갖을 수 있어서 받을 수 있는거임
    */
    @GetMapping("/test-get")
    fun getFormTest(@RequestParam("id") id: String): String{
    return "get : $id"
    }

    /**
     * fetch 방식으로 보낼때
     * 클라이언트에서 form 형식으로 body(쿼리파라미터랑 비슷한 형식)에 데이터를 담아 서버로 보낼때
     * 지원하지 않음 -> 오류 발생하는 컨트롤러
    */
    @PatchMapping("/test-patch")
    fun patchFormTest(@RequestBody request: String): String{
    return "patch : $request"
    }

    /**
     * get 방식으로 multipart file 보낼때
    */
    @GetMapping("/test-file")
    fun getFormFileTest(@RequestPart file: MultipartFile): String{
    return "file : ${file.name}, ${file.contentType}, ${file.resource}"
    }

    /**
     * post 방식으로 multipart file 보낼때
    */
    @PostMapping("/test-file")
    fun postFormFileTest(@RequestPart file: MultipartFile): String{
    return "file : ${file.name}, ${file.contentType}, ${file.resource}"
    }

    /**
     * 서버 to 서버
     * 앱 클라이언트
     * 웹 클라이언트 (form 대신, js를 이용한 AJAX 통신)
     *
     * post, put, patch 매서드로 body에 데이터를 담아서 서버로 요청
     * (delete, get -> body 담아서 데이터 보내는거 가능)
    */
    @PostMapping("/test-json")
    fun postJsonTest(@RequestBody request: String): String{
    return "post json : ${request}"
    }

    @PatchMapping("/test-json")
    fun patchJsonTest(@RequestBody request: String): String{
    return "patch json : ${request}"
    }

    @PutMapping("/test-json")
    fun putJsonTest(@RequestBody request: String): String{
    return "put json : ${request}"
    }

    @DeleteMapping("/test-json")
    fun deleteJsonTest(@RequestBody request: String): String{
    return "delete json : ${request}"
    }

    @GetMapping("/test-json")
    fun getJsonTest(@RequestBody request: String): String{
    return "get json : ${request}"
    }

    @DeleteMapping("/test")
    fun deleteList(@RequestParam id: List<Long>):String{
    id.forEach {
    print(it)
    }
    return "ok"
    }
    /**
     * @DeleteMapping("/test")
    fun deleteList(@RequestParam id: List<Long>):String{
    id.forEach {
    print(it)
    }
    return "ok"
    }
    */

    /**
     * ResponseEntity는 단순히 ResponseBody를 전달하는 것과 다르게 헤더 정보와 HTTP 상태 코드를 함께 가공하여 전달할 수 있다.
     * 또한 Body에 응답을 위한 별도의 클래스를 제작하여 사용하면 정해진 규약에 따라 데이터를 전달할 수 있는 장점이 있다.
    */
    @PostMapping("/test-http")
    fun getJsonResponseEntity(@RequestBody request: String): ResponseEntity<String> {
    return ResponseEntity("ok", HttpStatus.CREATED)
    }

     */


}