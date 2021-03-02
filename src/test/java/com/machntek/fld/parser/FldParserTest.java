package com.machntek.fld.parser;


import com.machntek.application.dto.FirstTest;
import com.machntek.fld.annotation.Fld;
import com.machntek.fld.annotation.FldElement;
import com.machntek.fld.dto.FldBody;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FldParserTest {
    String data = "abcde1234f0000099";

    @Test
    public void parseRequestBody_FldBody구현객체_객체로파싱() throws IOException {
        //given
        TestObject expectedObject = new TestObject();
        expectedObject.setTestString("abcde1234f");
        expectedObject.setTestNum(99);

        //when
        TestObject test = FldParser.parseRequestBody(data, TestObject.class);

        //then
        assertEquals(expectedObject.getTestString(), test.getTestString());
        assertEquals(expectedObject.getTestNum(), test.getTestNum());
    }

    @Fld
    static public class TestObject implements FldBody {
        @FldElement(order = 1, size = 10)
        private String testString;

        @FldElement(order = 2, size = 7)
        private Integer testNum;

        private String notFldElement;

        private List<FldBody> subBody;

        public String getTestString() {
            return this.testString;
        }
        public void setTestString(String testString) {
            this.testString = testString;
        }

        public Integer getTestNum() {
            return this.testNum;
        }
        public void setTestNum(Integer testNum) {
            this.testNum = testNum;
        }

    }
}
