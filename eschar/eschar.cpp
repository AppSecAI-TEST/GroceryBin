#include <iostream>

int main(){
	while(1){
		int c=std::cin.get();
		if(c==-1)
			return 0;
		char ch=(char)c;
		switch(ch){
			case '\'':
				std::cout<<"\\\'";
				break;
			case '\\':
				std::cout<<"\\\\";
				break;
			case '\"':
				std::cout<<"\\\"";
				break;
			case '\r':
				std::cout<<"\\r";
				break;
			case '\n':
				std::cout<<"\\n";
				break;
			case '\t':
				std::cout<<"\\t";
				break;
			default:
				std::cout<<ch;
		}
	}
}
