import api from "../api/axios";

export const getDashboard = async () => {
    const res = await api.get("/dashboard/summary");
    return res.data;
};