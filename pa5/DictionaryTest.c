/* 
*  DictionaryTest.c
*  Test client for the Dictionary ADT
*/

#include<stdio.h>
#include<stdlib.h>
#include"Dictionary.h"

int main(void){
	DictionaryRef D = newDictionary();
	
	insert(D,50,12);
	insert(D,12,12);
	insert(D,34,2);
	insert(D,23,1);
        insert(D,68,15);
        insert(D,112,16);
	insert(D,45,45);
        insert(D,33,67);
        insert(D,51,78);
        insert(D,58,23);
        insert(D,5,0);
        insert(D,99,13);
	
	printDictionary(D,stdout);

	delete(D,50);
	delete(D,23);
	delete(D,99);
	
	printDictionary(D,stdout);
	
	makeEmpty(D);
	printDictionary(D,stdout);

}
