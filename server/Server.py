from flask import Flask, render_template, request
import sqlite3 as sql
app = Flask(__name__)

@app.route('/test', methods = ["POST"])
def test():
    content = request.get_json(silent=True)
    print content
    return '{"test":"success"}'

if __name__ == '__main__':
    app.run("0.0.0.0", port=4000, debug = True)
