Rest Server for Media (Audio-files [ogg, wav, mp3, wma, mp4 etc..])
===================================================================

REST-based Java-server that exposes Music etc. Using Lucene to index files

# Information

This project is supposed to be able to do the following things:

* Serve media-files (Only audio as of the current version) that is located on your server (the host-machine)
* Start using a java -jar command
* Run as a REST-based service using Jersey and Jetty
* Use Lucene or other simple indexing-engines. Might be NoSQL-based too
* Show all elements in JSON
* Be able to serve/stream files through REST
* Fetch albumart etc from external resources

## Requirements

* Use minimal as a footprint regarding memory, cpu etc.
* Super-simple to start application

# Parts

## Database

* Will consist of the following elements:
    * Storage that contains all found elements within folders
    * Storage that stores all favorite places where stuff is stored (e.g My Music)

## REST

* Must do the following:
    * Serve all current searchable folders
    * Serve all files, sorted by various options
    * Serve all files - by folders
    * Serve numberOf
    * Be able to accept searches

# Contributors

Vegard Aasen - vegaasen@gmail.com - vegaasen @ github
