/*Dictionary.c
 *This program creates a Dictionary ADT by hashing with the function
 *(int)k mode (100)tableSize;
 *
 */

#include<stdio.h>
#include<stdlib.h>
#include<assert.h>

#include "Dictionary.h"
/*#define tableSize 100*/

/********************* Private Functions **********************/
const int tableSize=100;
/* creates the node data type*/
typedef struct Node{
	int key;
	int value;
	struct Node* next;
} Node;
typedef Node* NodeRef;

/*constructor for the node data type*/
NodeRef newNode(int x, int y){
	NodeRef N = malloc(sizeof(Node));
	assert(N!=NULL);
	N->key = x;
	N->value = y;
	N->next = NULL;
	return(N);
}

/*free's the heap memory for the node data type*/
void freeNode(NodeRef* pN){
	if(pN!=NULL && *pN!=NULL){
		free(*pN);
		*pN = NULL;
	}
}
/*creates the list data type*/
typedef struct List{
	NodeRef front;
	int size;
}List;
typedef List* ListRef;
/*constructor for the node data type*/
ListRef newList(void){
	ListRef L = malloc(sizeof(List));
	assert(L!=NULL);
	L->front = NULL;
	L->size = 0;
	return L;
}
/*free the heap memory from list*/
void freeList(ListRef* pL){
	free(*pL);
	*pL = NULL;
}
/*creates the Dictionary data type*/
typedef struct Dictionary{
	ListRef* table;
	int numItems;
}Dictionary;
/*hash function*/
int hash(int k){
	return (k%tableSize);
}

/********************** Public Funtions **********************/
/*Constructor for the Dictionary ADT */
DictionaryRef newDictionary(void){
	int i;
	DictionaryRef D = malloc(sizeof(Dictionary));
	assert(D!=NULL);
	D->table = calloc(tableSize+1,sizeof(ListRef));
	D->numItems = 0;
	for(i=0; i<tableSize; i++){
		D->table[i] = newList();
	}
	return D;	
}
/*free's heap memory for the Dicitonary ADT*/
void freeDictionary(DictionaryRef* pD){
		free(*pD);
		*pD = NULL;
}

/*isEmpty
 *Pre:Dictionary cannot be null
 *Pos:Determines if the list is empty
 */
int isEmpty(DictionaryRef D){
	if( D == NULL){
		fprintf(stderr,"Stack Error: calling isEmpty() on NULL DictionaryRef\n");
		exit(EXIT_FAILURE);
	}
	return (D->numItems == 0);
}

/*size
 *returns the size of the list
 */
int size(DictionaryRef D){
	if(D == NULL){
		fprintf(stderr,"Stack Error: calling size() on NULL DictionaryRef\n");
		exit(EXIT_FAILURE);
	}
	return (D->numItems);
}
/*lookup
 *Pre:None 
 *Pos:returns the value associated with the key
 */
int lookup(DictionaryRef D, int k){
	NodeRef N = D->table[hash(k)]->front;
	while(N!=NULL){
		if( N->key == k){
			return N->value;
		}
		N = N->next;
	}
	return UNDEF;
}
/*insert
 *Pre: Dictionary cant be NULL and cannot insert dublicate key
 *Pos: Inserts new key and value into the hash table
 */
void insert(DictionaryRef D, int k , int v){
	int h = hash(k);
	
	NodeRef P = newNode(k,v);
	if( D == NULL){
		fprintf(stderr,"Stack Error: calling insert() on NULL DictionaryRef\n");
		exit(EXIT_FAILURE);
	}
	if( lookup(D,k) != UNDEF){
		fprintf(stderr,"Cannot insert() dublicate key\n");
		exit(EXIT_FAILURE);
	}
	if( D->table[h]->front == NULL){
		D->table[h]->front = P;
	}
	else{
		P->next = D->table[h]->front;
		D->table[h]->front = P;
	}
	D->numItems++;
	D->table[h]->size++;
}
/*delete
 *Pre:Cannot delete non-existent key
 *Pos:Deletes a key from the stack
 */
void delete(DictionaryRef D, int k){
	int h = hash(k);
	NodeRef N = D->table[h]->front;
	NodeRef P = NULL;
	if( D == NULL){
		fprintf(stderr,"Stack Error: calling delete() on NULL DictionaryRef\n");
		exit(EXIT_FAILURE);
	}
	if( lookup(D,k) == UNDEF){
		fprintf(stderr,"Cannot delete() non-existant key\n");
		exit(EXIT_FAILURE);
	}
	if( D->table[h]->size == 1){
		freeNode(&N);
	}
	else if (N->key == k){
		P = N;
		D->table[h]->front = N->next;
		freeNode(&P);
	}
	else{
		while(N->next->key != k){
			N = N->next;
		}
		P = N->next;
		N->next = P->next;
		freeNode(&P);
	}
	D->numItems--;
	D->table[h]->size--;
}
/*makeEmpty
 *Pre:none
 *Pos:emptys the dictionary
 */
void makeEmpty(DictionaryRef D){
	if( D == NULL){
		fprintf(stderr,"Stack Error: calling makeEmpty() on NULL DictionaryRef\n");
		exit(EXIT_FAILURE);
	}
	int i;
	for(i=0; i<tableSize; i++){
		D->table[i]->size = 0; 
		freeNode(&D->table[i]->front);		
	}
	D->numItems = 0;

}
/*Prints the dictionary to the out file in order from table[0]...table[m-1]
 */
void printDictionary(DictionaryRef D, FILE* out){
	if( D == NULL){
		fprintf(stderr,"Stack Error: calling printDictionary() on NULL DictionaryRef\n");
		exit(EXIT_FAILURE);
	}
	NodeRef N = NULL;
	int i;
	for( i=0; i<tableSize; i++){
		N = D->table[i]->front;
		while( N!=NULL){
			fprintf(out,"%d %d\n",N->key,N->value);
			N = N->next;
		}
	}
}




