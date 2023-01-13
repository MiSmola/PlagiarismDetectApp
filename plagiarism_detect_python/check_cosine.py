import sys
from pysimilar import compare
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

firstFilePath = sys.argv[1]
secondFilePath = sys.argv[2]

firstFileExtracted = extractTextFromFile(firstFilePath)
secondFileExtracted = extractTextFromFile(secondFilePath)

print(compare(firstFileExtracted.lower(), secondFileExtracted.lower()))