# FurSPS Furniture Website

## Project Overview

FurSPS is a dynamic furniture website built using Servlets, JSP/JSTL, Bootstrap, JDBC, SQLServer, and decorated with Sitemesh. 
This project aims to provide an efficient and user-friendly platform for browsing and purchasing furniture items.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)
- [License](#license)

## Features

- User-friendly interface for browsing furniture items.
- Secure user authentication and authorization.
- Dynamic content generation using Servlets and JSP/JSTL.
- Responsive design with Bootstrap for a seamless user experience.
- Database connectivity using JDBC with support for MySQL.
- Decorated with Sitemesh for easy and consistent page layout.

## Prerequisites

Make sure you have the following installed:

- Java Development Kit (JDK)
- Apache Tomcat Server
- SQLServer Database
- IDE (Eclipse, IntelliJ, etc.)
- Git

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/LIIHERTZ/LTWeb_Nhom5.git
   ```

2. Import the project into your IDE.

3. Configure the database connection in `src/main/java/FurSPS/configs/DBConnection.java`.

4. Deploy the project to your Tomcat server.

## Configuration

1. Database Configuration:

   - Configure your database connection settings in `src/main/java/FurSPS/configs/DBConnection.java`.

2. Servlet Mapping:

   - Update servlet mappings in `web.xml` if needed.

## Usage

1. Start your Tomcat server.

2. Access the application at `http://localhost:8080/FurSPS_Nhom5`.

3. Explore the furniture items, login, and enjoy a seamless shopping experience.

## Technologies Used

- Java Servlets
- JSP/JSTL
- Bootstrap
- JDBC
- SQLServer
- Sitemesh

## Contributing

Contributions are welcome! Fork the repository and create a pull request with your improvements.

## License

This project is licensed under the MIT License.  
See the [MIT License](https://opensource.org/licenses/MIT) for more details.
