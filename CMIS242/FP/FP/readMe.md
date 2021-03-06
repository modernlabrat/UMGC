# Proper Syntax for loading objects via text file
1. Objects must be enclosed in [ ]: brackets.
2. Colon represents end of line, note the last field does not include 2 colons, but 1 after the closing "]" bracket.
3. All IDs must be unique
4. See test1.txt in /txts folder for load demo with correct syntax
5. Spaces are allowed between Media declarations (after ']:')

## 1. Data Types

> EBook|MovieDVD|MusicCD = String  
> ID = INT (length=6)  
> YEAR = INT (length=4)  
> COPIES = INT  
> PRICE = DOUBLE  
> STATUS = A|R|O  
> * A = Available
> * O = Out of Stock
>
>
> TITLE= STRING  
> NUMCHAPTERS= INT  
> HASCHAPTERS= BOOLEAN  
> WORDCOUNT= INT  
> GENRE = STRING  
> ARTIST = STRING  
> LENGTH = STRING (pattern=MM:ss)

## 2. EBOOK Syntax

> [ EBook:  
> ID=id:  
> YEAR=year:  
> COPIES=copies:  
> PRICE=price:  
> STATUS=A|O:  
> TITLE=title:  
> NUMCHAPTERS=number of chapters:  
> HASCHAPTERS=true|false:  
> WORDCOUNT=word count ]:

## 3. MovieDVD Syntax

> [ MovieDVD:  
> ID=id:  
> YEAR=year:  
> COPIES=copies:  
> PRICE=price:  
> STATUS=A|O:  
> TITLE=title:  
> GENRE=genre ]:  

## 4. MusicCD Syntax

> [ MusicCD:  
> ID=id:  
> YEAR=year:  
> COPIES=copies:  
> PRICE=price:  
> STATUS=A|O:  
> TITLE=title:  
> ARTIST=artist:  
> LENGTH=length ]:  