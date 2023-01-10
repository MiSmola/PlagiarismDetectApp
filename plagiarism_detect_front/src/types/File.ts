import User from "./User";

export default interface File {
    localPath: string
    size: number
    idUsers: User
}