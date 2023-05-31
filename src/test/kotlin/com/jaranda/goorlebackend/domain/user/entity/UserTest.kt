package com.jaranda.goorlebackend.domain.user.entity

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.ints.shouldBeGreaterThan

class UserTest : DescribeSpec({
    describe("랜덤 이름을 100번 호출하면") {
        val names = (0 until 100).map { generatedRandomName() }.toSet()
        it("적어도 1개 이상의 이름이 나와야한다.") {
            names.size shouldBeGreaterThan 1
        }
    }
})