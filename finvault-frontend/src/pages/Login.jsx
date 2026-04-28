import { useState } from "react";
import { login } from "../services/authService";
import {useNavigate} from "react-router-dom";


function Login() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");
    setLoading("true");

    try{
      await login({ email, password});
      navigate("/dashboard");
    } catch (err) {
      setError("Ivalid email or password");
      console.error(err);
    }
  };

  

  return (
    <div>
      <h2>FinVault Login</h2>

      <form onSubmit={handleLogin}>
      <div>
        <label>Email</label>
        <input
        type = "email"
        placeholder="Enter Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        required
        />
      </div>

      <div>
        <label>Password</label>
        <input
        type="password"
        placeholder="Enter Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        />
      </div>
          {error && <p style={ {colr: "red"}}> {error}</p>}

          <button type="submit" disabled={loading}>
            {loading ? "Loggin in...": "Login"}
          </button>
      </form>
      </div>
  );
}
    
export default Login;