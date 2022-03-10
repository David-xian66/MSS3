import axios from "axios";
import baseUrl from "../api/baseUrl"

const service = axios.create({
    baseURL: baseUrl,
    timeout: 15000,
})

export default service