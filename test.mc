/**
    Mini C has two types of comments: text comment and line comment.
*/
/// Mini C has two types of comments: text comment and line comment.
/*
    Mini C has two types of comments: text comment and line comment.
*/

void main()
{
  int i;
  char c;
  double d;
  string s;

  i = 100;	// i sets 100
  i = 0x1f;
  c = 'A';
  d = 123.;
  d = .123;
  d = 12.34;
  d = 12.3e+12;
  s = "string";

  switch(c){
     case 1:break;
     default: break;
  }
  write(i);
}
