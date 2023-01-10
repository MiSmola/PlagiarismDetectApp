import axios from "axios";

const fetchReport = (): Promise<any> => {
    return axios.get("http://localhost:8080/api/report")
        .finally(() => {
            console.log("Happy React Devs")
        })
};

const ReportService = {
    fetchReport
};

export default ReportService;