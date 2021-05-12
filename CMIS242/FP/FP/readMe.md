# Proper Syntax 
1. For loading objects via .txt file
2. Objects must be enclosed in [ ]: brackets.
3. Colon represents end of line, note the last field does not include 2 colons, but 1 after the closing "]" bracket.

## 1. Data Types
> EBook|MovieDVD|MusicCD = String<br />
> ID = INT (length=6)<br />
> YEAR = INT (length=4)<br />
> COPIES = INT<br />
> PRICE = DOUBLE:<br />
> STATUS = A|R|O<br />
> * A = Available
> * R = Rented
> * O = Out of Stock <br />
> TITLE= STRING<br />
> NUMCHAPTERS= INT<br />
> HASCHAPTERS= BOOLEAN<br />
> WORDCOUNT= INT<br />
> GENRE = STRING <br />
> ARTIST = STRING <br />
> LENGTH = STRING (pattern=MM:ss)<br />

## 2. EBOOK
> [ EBook:<br />
> ID=id:<br />
> YEAR=year:<br />
> COPIES=copies:<br />
> PRICE=price:<br />
> STATUS=A|R|O:<br />
> TITLE=title:<br />
> NUMCHAPTERS=number of chapters:<br />
> HASCHAPTERS=true|false: <br />
> WORDCOUNT=word count ]:<br />

## 3. MovieDVD
> [ MovieDVD:<br />
> ID=id:<br />
> YEAR=year:<br />
> COPIES=copies:<br />
> PRICE=price:<br />
> STATUS=A|R|O:<br />
> TITLE=title:<br />
> GENRE=genre ]:<br />

## 4. MusicCD
> [ MusicCD:<br />
> ID=id:<br />
> YEAR=year:<br />
> COPIES=copies:<br />
> PRICE=price:<br />
> STATUS=A|R|O:<br />
> TITLE=title:<br />
> ARTIST=artist:<br />
> LENGTH=length ]:<br />