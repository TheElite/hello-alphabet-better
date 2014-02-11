#include &lt;stdio.h&gt;
#include &lt;string.h&gt;
#include &lt;ctype.h&gt;

int maxCountIndex(int *p, int size)
{
    int i;
    int max = 0;
    int index = 0;
    
    for(i=0;i&lt;size;i++)
    if(p[i]&gt;max)
    {
        max=p[i];
        index=i;
    }
    return(index);
}
void main(void)
{
    FILE *in;
    char word[80];
    char startWith[80];
    char choiceArray[80];
    int letters[26];
    int startWithSize;
    int maxIdx;
    int i;
    
    printf("enter the starting letters to test: ");
    gets(startWith);
    
    for(i=0;startWith[i]!=0;i++)
    {
        startWith[i]=toupper(startWith[i]);
        if(startWith[i]&lt;'A' || startWith[i]&gt;'Z') //range check
        startWith[i]='A'; // make it some valid character
    }
    
    //      strcpy(startWith,"");
    startWithSize=strlen(startWith);
    memset(letters,0,sizeof(letters));
    memset(choiceArray,0,sizeof(choiceArray));
    
    in = fopen("words.txt","rt");
    if(in !=NULL)
    {
        while(fgets(word,sizeof(word),in) != NULL)
        if(strncmp(word,startWith,startWithSize) == 0) // found it
        break;
        if(word != NULL)
        {
            letters[word[startWithSize]-'A']++;
            while( fgets(word,sizeof(word),in) != NULL &&
            strncmp(word,startWith,startWithSize) == 0)
            {
                letters[word[startWithSize]-'A']++;
            }
            for(i=0;i&lt;sizeof(letters)/sizeof(letters[0]);i++)
            printf("%d ",letters[i]);
            printf("\n");
            printf("\n");
            maxIdx=maxCountIndex(letters,sizeof(letters)/sizeof(letters[0]));
            i=0;
            while(letters[maxIdx] != 0)
            {
                choiceArray[i]='A'+maxIdx;
                i++;
                //                         printf("%c",'A'+maxIdx);
                letters[maxIdx]=0;
                maxIdx=maxCountIndex(letters,sizeof(letters)/sizeof(letters[0]));
            }
            printf("%s\n",choiceArray);
            printf("\n");
        }
        else
        {
            printf("%s not found in dictionary\n",startWith);
        }
        fclose(in);
    }
}
