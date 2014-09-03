/* 
*  DictionaryClient.c
*  Test client for the Dictionary ADT
*/

#include<stdio.h>
#include<stdlib.h>
#include"Dictionary.h"

int main(int argc, char* argv[]){
   int i, n, m, p, key, value;
   FILE* in;
   FILE* out;
   DictionaryRef D = newDictionary();

   /* check command line for correct number of arguments */
   if( argc != 3 )
   {
      printf("Usage: %s infile outfile\n", argv[0]);
      exit(EXIT_FAILURE);
   }
   /* open files for reading and writing */
   in = fopen(argv[1], "r");
   out = fopen(argv[2], "w");
   if( in==NULL ){
      printf("Unable to open file %s for reading\n", argv[1]);
      exit(EXIT_FAILURE);
   }
   if( out==NULL ){
      printf("Unable to open file %s for writing\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   /* read number n of pairs from first line of input file */
   if( fscanf(in, " %d", &n) != 1 ){
      printf("Problem reading line 1 of input file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   /* read n (key, value) pairs from input file, then insert them in Dictionary D */
   for(i=1; i<=n; i++){
      if( fscanf(in, " %d %d", &key, &value)!=2 ){
         printf("Problem reading line %d of input file %s\n", i+1, argv[1]);
         exit(EXIT_FAILURE);
      }
      insert(D, key, value);
   }

   /* print Dictionary D to out */
   printDictionary(D, out);
   fprintf(out, "\n");

   /* read number m of keys to lookup from input file */
   if( fscanf(in, " %d", &m) != 1 ){
      printf("Problem reading line %d of input file %s\n", n+2, argv[1]);
      exit(EXIT_FAILURE);
   }

   /* read in m keys from input file and search for them in Dictionary D */
   for(i=1; i<=m; i++){
      if( fscanf(in, " %d", &key)!=1 ){
         printf("Problem reading line %d of input file %s\n", i+n+2, argv[1]);
         exit(EXIT_FAILURE);
      }
      value = lookup(D, key);
      fprintf(out, "key = %d ", key);
      value==UNDEF?
      fprintf(out, "not found\n"):
      fprintf(out, "value=%d\n", value);
   }
   fprintf(out, "\n");

   /* read number p of pairs to delete */
   if( fscanf(in, " %d", &p) != 1 ){
      printf("Problem reading line %d of input file %s\n", m+n+3, argv[1]);
      exit(EXIT_FAILURE);
   }

   /* read in p keys from input file and delete them from Dictionary D */
   for(i=1; i<=p; i++){
      if( fscanf(in, " %d", &key)!=1 ){
         printf("Problem reading line %d of input file %s\n", i+m+n+3, argv[1]);
         exit(EXIT_FAILURE);
      }
      value = lookup(D, key);
      if( value!=UNDEF ){
         delete(D, key);
         fprintf(out, "pair (%d, %d) deleted\n", key, value);
      }else{
         fprintf(out, "key %d not found\n", key);
      }
   }
   fprintf(out, "\n");

   /* call a few more ADT operations on D */
   fprintf(out, "isEmpty(D) = %s\n", isEmpty(D)?"true":"false");
   fprintf(out, "size(D) = %d\n", size(D));
   makeEmpty(D);
   fprintf(out, "isEmpty(D) = %s\n", isEmpty(D)?"true":"false");
   printDictionary(D, out);

   freeDictionary(&D);
   fclose(in);
   fclose(out);
   return(EXIT_SUCCESS);
}

