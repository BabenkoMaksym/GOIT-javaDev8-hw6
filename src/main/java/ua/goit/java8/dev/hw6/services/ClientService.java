package ua.goit.java8.dev.hw6.services;

import org.apache.log4j.Logger;
import ua.goit.java8.dev.hw6.Database;
import ua.goit.java8.dev.hw6.dao.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private static final Logger LOG = Logger.getLogger(ClientService.class);

    public Long create(String name) {
        Long clientId = null;
        String sql = "INSERT INTO public.client (client_name) VALUES (?)";

        try (Connection conn = Database.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, name);
            st.executeUpdate();

            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                clientId = generatedKeys.getLong("id");
            }
            LOG.info("Create client successful");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clientId;
    }

    public String getById(long id) {
        String clientName = null;
        String sql = "SELECT id, client_name FROM public.client WHERE id = ?";

        try (Connection conn = Database.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setLong(1, id);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                clientName = resultSet.getString("client_name");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clientName;
    }

    public void setName(Long id, String name) {
        String sql = "UPDATE public.client SET client_name = ? WHERE id = ?";

        try (Connection conn = Database.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, name);
            st.setLong(2, id);
            st.execute();
            LOG.info(String.format("Update client name = %s where client id = %d", name, id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteById(long id) {
        String sql = "DELETE FROM public.client WHERE id = ?";

        try (Connection conn = Database.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setLong(1, id);
            st.execute();
            LOG.info(String.format("Delete client with client id = %d", id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT id, client_name FROM public.client ORDER BY id";

        try (Connection conn = Database.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                Long clientID = resultSet.getLong("id");
                String clientName = resultSet.getString("client_name");
                Client client = new Client(clientID, clientName);
                clients.add(client);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }


}
