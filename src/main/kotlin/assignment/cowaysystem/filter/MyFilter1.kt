package assignment.cowaysystem.filter

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

class MyFilter1 : Filter{
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        println("filter1")
        chain?.doFilter(request, response)
    }
}