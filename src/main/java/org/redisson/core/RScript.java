/**
 * Copyright 2014 Nikita Koksharov, Nickolay Borbit
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redisson.core;

import io.netty.util.concurrent.Future;

import java.util.List;

import org.redisson.client.protocol.RedisCommand;
import org.redisson.client.protocol.RedisCommands;

public interface RScript {

    enum ReturnType {
        BOOLEAN(RedisCommands.EVAL_BOOLEAN),
        INTEGER(RedisCommands.EVAL_INTEGER),
        MULTI(RedisCommands.EVAL_LIST),
        STATUS(RedisCommands.EVAL_STRING),
        VALUE(RedisCommands.EVAL_OBJECT),
        MAPVALUE(RedisCommands.EVAL_MAP_VALUE),
        MAPVALUELIST(RedisCommands.EVAL_MAP_VALUE_LIST);

        RedisCommand<?> command;

        ReturnType(RedisCommand<?> command) {
            this.command = command;
        }

        public RedisCommand<?> getCommand() {
            return command;
        }

    };

    List<Boolean> scriptExists(String ... shaDigests);

    Future<List<Boolean>> scriptExistsAsync(String ... shaDigests);

    boolean scriptFlush();

    Future<Boolean> scriptFlushAsync();

    boolean scriptKill();

    Future<Boolean> scriptKillAsync();

    String scriptLoad(String luaScript);

    Future<String> scriptLoadAsync(String luaScript);

    <R> R evalSha(String shaDigest, ReturnType returnType);

    <R> R evalSha(String shaDigest, ReturnType returnType, List<Object> keys, Object... values);

    <R> Future<R> evalShaAsync(String shaDigest, ReturnType returnType, List<Object> keys, Object... values);

    <R> Future<R> evalAsync(String luaScript, ReturnType returnType, List<Object> keys, Object... values);

    <R> R eval(String luaScript, ReturnType returnType);

    <R> R eval(String luaScript, ReturnType returnType, List<Object> keys, Object... values);

}
