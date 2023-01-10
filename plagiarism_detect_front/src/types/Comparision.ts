import File from "./File";

export default interface Comparision {
    comparisonCreationDate: string
    levenshteinCoefficient: string
    matcherCoefficient: string
    levenshteinCoefficientPercentage: string
    matcherCoefficientPercentage: string
    sourceFileName: string
    sourceFileCreationDate: string
    targetFileName: string
    targetFileCreationDate: string
}