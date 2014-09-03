/*
*  Dictionary.h
*  Header file for the Dictionary ADT
*/

#if !defined(_DICTIONARY_H_INCLUDE_)
#define _DICTIONARY_H_INCLUDE_

#define UNDEF -1

/*
*  DictionaryRef
*  Exported reference type which points to a Dictionary struct
*/
typedef struct Dictionary* DictionaryRef;

/*
*  newDictionary
*  Allocates and initializes a struct representing an empty Dictionary.  Returns a
*  pointer to new memory if successful, terminates if not successful.
*/
DictionaryRef newDictionary(void);

/*
*  freeDictionary
*  Frees all heap memory associated with *pD and sets the reference *pD to NULL
*/
void freeDictionary(DictionaryRef* pD);

/*
*  isEmpty
*  pre: none
*  post: returns 1 (true) if D is empty, 0 (false) otherwise
*/
int isEmpty(DictionaryRef D);

/*
*  size
*  pre: none
*  post: returns the number of entries in D
*/
int size(DictionaryRef D);

/*
*  lookup
*  pre: none
*  post: returns value associated key k, or UNDEF if no such key exists
*/
int lookup(DictionaryRef D, int k);

/*
*  insert
*  inserts new (key,value) pair into D
*  pre: key k currently does not exist in D, i.e. lookup(D, k)==UNDEF
*  post: !isEmpty(D), size(D) is increased by one
*/
void insert(DictionaryRef D, int k, int v);

/*
*  delete
*  deletes pair with the key k
*  pre: key k currently exists in D, i.e. lookup(D, k)!=UNDEF
*  post: size(D) is decreased by one
*/
void delete(DictionaryRef D, int k);

/*
*  makeEmpty
*  pre: none
*  post: isEmpty(D)
*/
void makeEmpty(DictionaryRef D);

/*
*  printDictionary
*  pre: none
*  post: prints a text representation of D to file pointed to by out.
*/
void printDictionary(DictionaryRef D, FILE* out);


#endif



