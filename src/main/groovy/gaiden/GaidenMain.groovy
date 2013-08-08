/*
 * Copyright 2013 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gaiden

import gaiden.command.GaidenBuild
import gaiden.command.GaidenClean

/**
 * A command line to execute Gaiden.
 *
 * @author Hideki IGARASHI
 * @author Kazuki YAMAMOTO
 */
class GaidenMain {

    private static final String CONFIG_PATH = "Config.groovy"
    private static final String USAGE_MESSAGE = """\
Usage: gaiden <command>

   commands:
       build  Build pages from source pages
       clean  Clean the build directory
"""

    /**
     * A main command line interface.
     *
     * @param args all command line args
     */
    static void main(String... args) {
        new GaidenMain().run(args)
    }

    void run(String... args) {
        if (!args) {
            usage()
            return
        }

        new GaidenConfigInitializer().initialize(new File(CONFIG_PATH))

        switch (args.first()) {
            case "build":
                new GaidenBuild().execute()
                break
            case "clean":
                new GaidenClean().execute()
                break
            default:
                usage()
                return
        }
    }

    private void usage() {
        println USAGE_MESSAGE
    }

}