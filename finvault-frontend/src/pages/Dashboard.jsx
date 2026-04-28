import { useEffect, useState } from "react";
import { getDashboard } from "../services/dashboardService";

function Dashboard() {
  const [data, setData] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await getDashboard();
        setData(res);
      } catch (err) {
        console.error(err);
      }
    };

    fetchData();
  }, []);

  if (!data) return <h2>Loading dashboard...</h2>;

  return (
    <div>
      <h2>Dashboard</h2>

      <p>KYC Status: {data.kyc.status}</p>
      <p>Total Spent: ₹{data.totalSpent}</p>

      <h3>Category Spending</h3>
      {Object.entries(data.categoryWiseSpending || {}).map(([key, value]) => (
        <p key={key}>
          {key}: ₹{value}
        </p>
      ))}

      <h3>Recent Payments</h3>
      {data.recentPayments.map((p, index) => (
        <p key={p.id || index}>
          {p.receiverName} - ₹{p.amount}
        </p>
      ))}
    </div>
  );
}

export default Dashboard;