from flask import Flask
import sqlite3 as sql
app = Flask(__name__)

@app.route('/test')
def test():
    return 'success'

if __name__ == '__main__':
    app.run("0.0.0.0", port=4500, debug = True)
    
