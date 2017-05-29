%option noyywrap
%{
#include <stdio.h>
#include <string.h>
%}

%%
[ \t\n]+ {
}

"int" {
  printf("TYPENAME ");
}

"double" {
  printf("TYPENAME ");
}

"while" {
  printf("WHILE ");
}

"main" {
  printf("MAIN ");
}

[#][a-zA-Z]+["_"]?[0-9]* {
  printf("INCLUDE ");
}

[<][a-zA-Z]+[.]?[a-zA-Z]+[>] {
  printf("INCLUDE_FILE ");
}

[a-zA-Z]+["_"]?[0-9]* {
  printf("IDENTIFIER ");
}

"0b"[01]+ {
  printf("BINARY ");
}

"0x"[0-9A-Fa-f]+ {
  printf("HEXADECIMAL ");
}

"0"[0-7]+ {
  printf("OCTAL ");
}

0|([+-]?[1-9][0-9]*) {
  printf("INTEGER ");
}

[+-]?[0-9]+[\.]?[0-9]*([eE][+-])?[0-9]* {
  printf("REAL ");
}

"=" {
  printf("OPERATOR ");
}

"+" {
  printf("OPERATOR ");
}

"++" {
  printf("OPERATOR ");
}

"-" {
  printf("OPERATOR ");
}

"--" {
  printf("OPERATOR ");
}

"*" {
  printf("OPERATOR ");
}

"/" {
  printf("OPERATOR ");
}

"<" {
  printf("OPERATOR ");
}

">" {
  printf("OPERATOR ");
}

")" {
  printf("RIGHT_PARENTHESIS ");
}

"(" {
  printf("LEFT_PARENTHESIS ");
}

"}" {
  printf("RIGHT_BRACE ");
}

"{" {
  printf("LEFT_BRACE ");
}

";" {
  printf("SEMICOLON ");
}

"," {
  printf("COMMA ");
}
%%

int main() {
  yylex();
  printf("\n");
  return 0;
}
