import Comparision from "./Comparision";

export default interface Report {
    id: number
    title: string
    creationDate: string
    comparisonDtoList: Array<Comparision>
}