import Levenshtein as lev
import sys
import re
import docx2txt
from PyPDF2 import PdfReader

def extractTextFromFile(filepath):
    if filepath.endswith(".docx") or filepath.endswith(".doc"):
        extractedText = docx2txt.process(filepath)
        delNewline = extractedText.replace('\n', ' ')
        processedDoc = re.sub(' +', ' ', delNewline)
        return processedDoc
    if filepath.endswith(".pdf"):
        reader = PdfReader(filepath)
        text = ""
        for page in reader.pages:
            text += page.extract_text()
        newtext = text.replace('\n', ' ')
        processedPdf = re.sub(' +', ' ', newtext)
        return processedPdf
    else:
        textFile = open(filepath)
        extractedTxt = textFile.read()
        textFile.close()
        return extractedTxt


FirstFilePath = sys.argv[1]
SecondFilePath = sys.argv[2]

firstFileExtracted = extractTextFromFile(FirstFilePath)
secondFileExtracted = extractTextFromFile(SecondFilePath)

DistanceLeven = lev.distance(firstFileExtracted.lower(), secondFileExtracted.lower())
print(DistanceLeven)


