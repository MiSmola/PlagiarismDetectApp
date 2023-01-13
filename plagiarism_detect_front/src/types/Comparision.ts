import File from "./File";

export default interface Comparision {
    comparisonCreationDate: string
    levenshteinCoefficient: string
    jaroWinklerCoefficient: string
    cosineSimilarity: string
    sourceFileName: string
    sourceFileCreationDate: string
    targetFileName: string
    targetFileCreationDate: string
}