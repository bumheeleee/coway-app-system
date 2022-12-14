package assignment.cowaysystem.filter

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MyFilter3: Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val req = request as HttpServletRequest
        val res = response as HttpServletResponse
        /**
         * 토큰 "cos" 이걸 우리는 이제 만들어줘야됨! -> 언제?? id, pw가 정상적으로 들어와서 로그인이 완료되면 토큰을 만들어주고 응답을 해줘야됨
         * 클라이언트는 요청 할때마다 header Authorization에 value값으로 토큰을 가지고 온다.
         * 그 다음 넘어온 토큰이 내가 만든 토큰인지 검증만 하면됨 (HS256, RSA)
         */

        //토큰 : cos
        if (req.method == "POST"){
            val headerAuth = req.getHeader("Authorization")
            println(headerAuth)
            println("filter3")

            if (headerAuth == "cos"){
                chain?.doFilter(req, res)
            }else{
                val out = res.writer
                out.println("인증안됨")
            }
        }
    }
}