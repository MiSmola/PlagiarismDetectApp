import React, {useState, useEffect} from "react";
import UploadService from "../services/FileUploadService";
import IFile from "../types/IFile";
import ReportService from "../services/ReportService";
import axios from "axios";
import Report from "../types/Report";
import {Container, Table} from "reactstrap";

import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';

import {
    Box, Button,
    Collapse,
    IconButton,
    Paper,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography,
} from "@mui/material";

const RepotView: React.FC = () => {
    // const [currentFile, setCurrentFile] = useState<File>();
    // const [progress, setProgress] = useState<number>(0);
    // const [message, setMessage] = useState<string>("");
    // const [fileInfos, setFileInfos] = useState<Array<IFile>>([]);
    const [reportList, setReportList] = useState<Array<Report>>([]);


    console.log("Don't worry, be happy and say: " + reportList)

    function createData() {
        return reportList[0];
    }

    const action = () => {
        axios.get("http://localhost:8080/api/report")
            .then(res => {
                const reportsData = res.data;
                setReportList(reportsData);
            })
            .finally(() => {
                console.log("Happy React Devs")
            })
    };

    function Row(props: { rowie: ReturnType<typeof createData> }) {
        const {rowie} = props;
        const [open, setOpen] = React.useState(false);

        return (


            <React.Fragment>
                <TableRow sx={{'& > *': {borderBottom: 'unset'}}}>
                    <TableCell>
                        <IconButton
                            aria-label="expand row"
                            size="small"
                            onClick={() => setOpen(!open)}
                        >
                            {open ? <KeyboardArrowUpIcon/> : <KeyboardArrowDownIcon/>}
                        </IconButton>
                    </TableCell>
                    <TableCell align="left">{rowie.title}</TableCell>
                    <TableCell align="right">{rowie.id}</TableCell>
                    <TableCell align="right">{new Date(rowie.creationDate).toLocaleString()}</TableCell>


                </TableRow>
                <TableRow>
                    <TableCell style={{paddingBottom: 0, paddingTop: 0}} colSpan={6}>
                        <Collapse in={open} timeout="auto" unmountOnExit>
                            <Box sx={{margin: 1}}>
                                <Typography variant="h6" gutterBottom component="div">
                                    History
                                </Typography>
                                <Table size="small" aria-label="purchases">
                                    <TableHead>
                                        <TableRow>
                                            <TableCell>Date</TableCell>
                                            <TableCell>Source file</TableCell>
                                            <TableCell align="right">Target File</TableCell>
                                            <TableCell align="right">Levenshtein coefficient</TableCell>
                                            <TableCell align="right">Levenshtein %</TableCell>
                                            <TableCell align="right">Matcher coefficient</TableCell>
                                            <TableCell align="right">Matcher %</TableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {rowie.comparisonDtoList.map((historyRow) => (
                                            <TableRow key={historyRow.comparisonCreationDate}>
                                                <TableCell component="th" scope="row">
                                                    {new Date(historyRow.comparisonCreationDate).toLocaleString()}
                                                </TableCell>
                                                <TableCell>{historyRow.sourceFileName}</TableCell>
                                                <TableCell align="right">{historyRow.targetFileName}</TableCell>
                                                <TableCell align="right">{historyRow.levenshteinCoefficient}</TableCell>
                                                <TableCell align="right">{historyRow.levenshteinCoefficientPercentage}</TableCell>
                                                <TableCell align="right">{historyRow.matcherCoefficient}</TableCell>
                                                <TableCell align="right">{historyRow.matcherCoefficientPercentage}</TableCell>
                                            </TableRow>
                                        ))}
                                    </TableBody>
                                </Table>
                            </Box>
                        </Collapse>
                    </TableCell>
                </TableRow>
            </React.Fragment>
        );
    }

    return (
        <div>

            {/*<Container fluid>*/}
            {/*    <h3>Reports</h3>*/}
            {/*    <Table className="mt-4">*/}
            {/*        <thead>*/}
            {/*        <tr>*/}
            {/*            <th>Title</th>*/}
            {/*            <th>Creation Date</th>*/}
            {/*            <th>JSON shit</th>*/}
            {/*        </tr>*/}
            {/*        </thead>*/}
            {/*        <tbody>*/}
            {/*        {reportList &&*/}
            {/*            reportList.map(report => (*/}
            {/*                <tr key={report.id}>*/}
            {/*                    <td style={{whiteSpace: 'nowrap'}}>{report.title}</td>*/}
            {/*                    <td>{report.creationDate}</td>*/}
            {/*                    <td>{JSON.stringify(report.comparisons)}</td>*/}
            {/*                </tr>*/}
            {/*            ))}*/}
            {/*        </tbody>*/}
            {/*    </Table>*/}
            {/*</Container>*/}
            <button
                className="btn btn-success btn-sm"
                onClick={action}
            >
                Get reports
            </button>
            <TableContainer component={Paper}>
                <Table aria-label="collapsible table">
                    <TableHead>
                        <TableRow>
                            <TableCell/>
                            <TableCell align="left">Report</TableCell>
                            <TableCell align="right">Report ID</TableCell>
                            <TableCell align="right">Creation Date</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {reportList.map((row) => (
                            <Row key={row.title} rowie={row}/>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>


        </div>
    );
};

export default RepotView;