package com.example.demo.validation;

import jakarta.validation.GroupSequence;

import com.example.demo.validation.group.EmailGroup;
import com.example.demo.validation.group.NotBlankGroup;
import com.example.demo.validation.group.PatternGroup;
import com.example.demo.validation.group.SizeGroup;

// バリデーション制御用のアノテーション。()内の順序でエラーが出た時点のみエラーを1つ出力。
@GroupSequence({NotBlankGroup.class,EmailGroup.class,PatternGroup.class,SizeGroup.class})
public interface ValidationOrder {

}
