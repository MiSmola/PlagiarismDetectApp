import http from "../http-common";

const upload = (file: File, onUploadProgress: any): Promise<any> => {
    let formData = new FormData();

    formData.append("fileSource", file);

    return http.post("/api/task?userEmail=" + "example@example.com", formData, {
        headers: {
            "Content-Type": "multipart/form-data",
        },
        onUploadProgress,
    });
};

// const getFiles = () : Promise<any> => {
//     return http.get("/files");
// };

const FileUploadTaskService = {
    upload,
    // getFiles,
};

export default FileUploadTaskService;