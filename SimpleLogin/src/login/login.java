package login;

import javax.swing.JOptionPane;

/**
 * The login class extends User and handles the authentication process
 * through a login prompt, security questions, and password reset functionality.
 */
public class login extends User {

    /**
     * The main method serves as the entry point of the login system.
     * It initializes a User object, prompts for login credentials, 
     * and handles authentication and password reset if needed.
     */
    public static void main(String[] args) {
        User user = new User(); // Create a user object
        user.initialize(); // Initialize by reading information from a text file
        core(user);
    } // end main

    /**
     * Handles the core authentication flow, including login attempts and fallback authentication.
     *
     * @param Username The User object containing user credentials.
     */
    public static void core(User Username) {
        boolean isloggedin = loginprompt(Username); // Check if login is successful

        if (isloggedin) {
            JOptionPane.showMessageDialog(null, "Login was successful!");
        } else {
            JOptionPane.showMessageDialog(null, "No attempts remaining. Please authenticate identity.");
            boolean isauth = authenticate(Username.getMothername(), Username.getPetname()); // Security questions

            if (isauth) {
                // Allow the user to set a new password
                newpass(Username, Username.getPassword());
            } else {
                JOptionPane.showMessageDialog(null, "Authentication failed. Terminating program.");
            }
        }
    }

    /**
     * Prompts the user to enter a username and password, allowing up to three attempts.
     *
     * @param user The User object containing stored credentials.
     * @return true if login is successful, false otherwise.
     */
    private static Boolean loginprompt(User user) {
        int attempts = 3;
        while (attempts > 0) {
            String inputValue = JOptionPane.showInputDialog("Input Username:");
            if (inputValue.equals(user.username)) {
                inputValue = JOptionPane.showInputDialog("Input Password:");
                if (inputValue.equals(user.password)) {
                    return true;
                }
            }
            attempts--;
            JOptionPane.showMessageDialog(null, "Login unsuccessful. Attempts remaining: " + attempts);
        }
        return false;
    }

    /**
     * Asks the user security questions (mother's name and pet's name) to verify identity.
     *
     * @param mothername The stored mother's name.
     * @param petname The stored pet's name.
     * @return true if authentication is successful, false otherwise.
     */
    private static Boolean authenticate(String mothername, String petname) {
        String inputValue = JOptionPane.showInputDialog("Input mother's name:");
        if (inputValue.equals(mothername)) {
            JOptionPane.showMessageDialog(null, "Mother's name accepted.");
            String pet = JOptionPane.showInputDialog("Input pet's name:");
            if (pet.equals(petname)) {
                JOptionPane.showMessageDialog(null, "Pet's name accepted. Authentication will continue.");
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Allows the user to set a new password after verifying the current one.
     * The user has up to three attempts to enter a matching new password.
     *
     * @param user The User object whose password will be updated.
     * @param currentpass The user's current password.
     */
    private static void newpass(User user, String currentpass) {
        String confirmpass = JOptionPane.showInputDialog("Please confirm current password:");
        if (confirmpass.equals(currentpass)) {
            JOptionPane.showMessageDialog(null, "Current password accepted.");
            int tries = 3;
            while (tries > 0) {
                String newpass = JOptionPane.showInputDialog("Please enter a new password:");
                String newpassconfirm = JOptionPane.showInputDialog("Please confirm the new password:");

                if (newpass.equals(newpassconfirm)) {
                    user.setPassword(newpass);
                    JOptionPane.showMessageDialog(null, "New password accepted. Please try logging in again.");
                    core(user);
                    return;
                } else {
                    tries--;
                    JOptionPane.showMessageDialog(null, "Error! New passwords do not match, please try again. Attempts remaining: " + tries);
                }
            }
            JOptionPane.showMessageDialog(null, "Too many failed attempts. Password change failed. Program terminating.");
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect current password. Terminating program.");
        }
    }
}
