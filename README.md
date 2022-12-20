# Custom-Commands

Custom-Commands is a Paper plugin designed to make user be able to make aliases to multiple commands

## Get JAR file

To get .jar file you need to download it or build it

### Download

Download .jar file from releases

### Build

You need to have Maven installed to be able to compile this

```sh
git clone https://github.com/Adamekka/customcommands
cd customcommands/
mvn package -f "./pom.xml"
```

## Setup

Copy .jar file placed in ./target/ to your server folder in plugins  
Something like this:

```sh
cp ./target/customcommands-0.0.1.jar <server>/plugins/
```

## Config

To configure this plugin you need to edit config.yml in your server/plugins/Custom-Commands/ folder  
Something like this:

```sh
vim <server>/plugins/Custom-Commands/config.yml
```

Config.yml:

```yml
# How to use this plugin?
# It's simple

#############################################
#                  EXAMPLE                  #
#############################################
#commands:                                  #
#  <some-name>:                             #
#    executes:                              #
#        - "say hi"                         #
#        - "say welcome to our server"      #
#    description: "Some description"        #
#    aliases:                               #
#        - "greet-players"                  #
#        - "welcome-players"                #
#############################################

# Commands you define in "aliases" will execute commands you defined in "executes"

# Command "greet-players" will execute commands "say hi" and "say welcome to our server"
# Same goes for command "welcome-players"

commands:
  welcome:
    executes:
      - "say hi"
      - "say welcome to our server"
    description: "Some description"
    aliases:
      - "greet-players"
      - "welcome-players"
```
